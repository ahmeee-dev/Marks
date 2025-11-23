import os
from typing import AsyncGenerator, Optional

from sqlalchemy.ext.asyncio import (
	AsyncEngine,
	AsyncSession,
	async_sessionmaker,
	create_async_engine,
)

# Read the database URL from the environment. If not provided, fall back to a
# local SQLite database using aiosqlite which works well for development.
DATABASE_URL: str = os.getenv("DATABASE_URL", "sqlite+aiosqlite:///./dev.db")

# Create async engine and sessionmaker (session factory)
engine: AsyncEngine = create_async_engine(DATABASE_URL, echo=False, future=True)
sessionLocal: async_sessionmaker[AsyncSession] = async_sessionmaker(
	bind=engine, expire_on_commit=False, class_=AsyncSession
)


async def get_db_async() -> AsyncGenerator[AsyncSession, None]:
	"""FastAPI dependency that yields an async DB session.

	Usage in a route:

		from backend.src.utils.utils import get_db_async
		from sqlalchemy.ext.asyncio import AsyncSession

		@router.post(...)
		async def create(..., db: AsyncSession = Depends(get_db_async)):
			await db.execute(...)

	The function will open a session and ensure it is closed after use.
	"""
	async with sessionLocal() as session:
		yield session