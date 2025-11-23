from fastapi import HTTPException, status
from src.users.models import User, UserStatus
from src.users.schemas import UserCreate
from src.users.repository import UserRepository
from sqlalchemy.ext.asyncio import AsyncSession
import secrets

class AuthService:

	def __init__(
		self,
		user_repository: UserRepository,  # Type: UserRepository
	) -> None:
		self.user_repository = user_repository
		self.password_hasher = PasswordHasher() #TODO
		self.token_manager = TokenManager() #TODO
		self.email_sender = get_email_sender() #TODO


	async def register(
			self,
			*,
			session: AsyncSession,
			email: str,
			password: str
	)-> dict:
		"""Registers a new user.
		 Args:
            session: Database session
            email: User email
            password: User password (will be hashed)
            
        Returns:
            dict: Message and user_id
            
        Raises:
            HTTPException: If email already exists"""
		existing = await self.user_repository.get_by_email(session=session, email=email)
		if existing is not None:
			raise HTTPException(
				status_code=status.HTTP_409_CONFLICT, detail="Email already in use"
			)
		
		password_hash = self.password_hasher.hash_pasword(password)

		#Generate verification token
		verification_token = secrets.token_urlsafe(32)
		if verification_token is None:
			raise HTTPException(
				status_code=status.HTTP_400_BAD_REQUEST,
				detail="Error generating verification token"
			)
		
		#Creates user with INACTIVE status - they need to verify email first
		user = await self.user_repository.create(
			session=session,
			email=email,
			password_hash=password_hash,
			status=UserStatus.INACTIVE.value,
		)

		await self.user_repository.set_verification_token(
			session=session, user=user, token=verification_token
		)

		#Send verification email
		await self.email_sender.send_verification_email(email, verification_token)

		return {
			"message": "Registration successful. Please check your email to verify your account",
			"user_id": str(user.id)
		}

def get_auth_service() -> AuthService:
    """Factory for dependency injection of `AuthService`."""
    return AuthService(UserRepository())