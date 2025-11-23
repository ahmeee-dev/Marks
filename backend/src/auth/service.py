from fastapi import HTTPException
from src.users.models import User
from src.users.schemas import UserCreate
from sqlalchemy.ext.asyncio import AsyncSession

def registration_service(user: UserCreate, session: AsyncSession):
	# anti bot to implement later on

	
	raise HTTPException(status_code=400, detail="Not implemented")