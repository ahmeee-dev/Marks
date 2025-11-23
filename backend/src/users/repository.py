from __future__ import annotations
import uuid
from datetime import datetime, timedelta, timezone
from typing import Optional
from sqlalchemy import select
from sqlalchemy.exc import IntegrityError
from sqlalchemy.ext.asyncio import AsyncSession
from src.users.models import User, UserStatus

class UserRepository:
	"""Repository for User model"""
	
	async def get_by_id(self, *,session: AsyncSession, user_id: uuid.UUID)-> Optional[User]:
		"""Get user by id"""

		stmt = (select(User).where(User.id == user_id))
		result = await session.execute(stmt)
		
		return result.scalar_one_or_none()
	
	async def get_by_email(self, *,session: AsyncSession, email: str)-> Optional[User]:
		"""Get user by id"""

		email = email.lower()
		stmt = (select(User).where(User.email == email))
		result = await session.execute(stmt)

		return result.scalar_one_or_none()
	
	async def create(
			self,
			*,
			session: AsyncSession,
			email: str,
			password_hash: str,
			status: str = UserStatus.INACTIVE.value,
			name: Optional[str] = None
	) -> User:
		"""Create a user"""
		user = User(
			email=email,
			name = name,
			password_hash=password_hash,
			status=status
		)
		session.add(user)

		try:
			await session.commit()
		except IntegrityError:
			raise
		await session.refresh(user)
		return user

	async def update(
			self,
			*,
			session: AsyncSession,
			user: User,
			name: Optional[str] = None,
			password_hash: Optional[str] = None,
			status: Optional[str] = None
	) -> User:
		"""Update mutable fileds. Saves and returns new user"""
		if name is not None:
			user.name = name
		if password_hash is not None:
			user.password_hash = password_hash
		if status is not None:
			user.status = status

		try:
			await session.commit()
		except IntegrityError:
			await session.rollback()
			raise
		await session.refresh(user)
		return user

	async def delete(
			self, 
			*,
			session: AsyncSession,
			user: User
	) -> None:
		"""Guess what? Deletes the user passwed as a parameter"""
		await session.delete(user)
		try:
			await session.commit()
		except IntegrityError:
			session.rollback()
			raise

	async def get_by_verification_token(
			self,
			*, 
			session: AsyncSession,
			token: str
	) -> User:
		"""Gets a user from their verification token"""
		stmt = (select(User).where(User.email_verification_token == token))
		result = await session.execute(stmt)
		return result.scalar_one_or_none()


	async def set_verification_token(
			self,
			*,
			session: AsyncSession,
			user: User,
			token: str,
			expiration_hours: int = 24
	) -> User:
		"""Set email verification token for user"""
		user.email_verification_token = token
		user.email_verification_token_expiration = datetime.now(timezone.utc) + timedelta(hours=expiration_hours)

		try:
			await session.commit()
		except IntegrityError:
			await session.rollback()
			raise
		await session.refresh(user)
		return user

	async def verify_email(self,
						*,
						session: AsyncSession,
						user: User) -> User:
		"""Marks user account as email-verified"""
		user.status = UserStatus.ACTIVE.value
		user.email_verification_token = None
		user.email_verification_token_expiration = None

		try:
			await session.commit()
		except IntegrityError:
			await session.rollback()
			raise
		await session.refresh()
		return user

	async def set_password_reset_token(
			self,
			*,
			session: AsyncSession,
			user: User,
			token: str,
			expiration_hours: int = 1
	) -> User:
		"""Set password reset token for user"""
		user.password_reset_token = token
		user.password_reset_token_expiration = expiration_hours

		try:
			session.commit()
		except IntegrityError:
			await session.rollback()
			raise
		await session.refresh(user)
		return user

	async def get_by_password_reset_token(
			self,
			*,
			session: AsyncSession,
			token: str
	) -> Optional[User]:
		"""Get user by reset token"""
		stmt = select(User).where(User.password_reset_token == token)
		result = await session.execute(stmt)
		return result.scalar_one_or_none()
	

	async def clear_password_reset_token(
			self,
			*,
			session: AsyncSession,
			user: User
	) -> User:
		"""Clear password reset token after successful reset"""
		user.password_reset_token = None
		user.password_reset_token_expiration = None

		try:
			await session.commit()
		except IntegrityError:
			await session.rollback()
			raise
		await session.refresh(user)
		return user