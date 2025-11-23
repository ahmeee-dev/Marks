from pydantic import BaseModel
from sqlalchemy import Boolean, DateTime, TEXT, text, Enum as SQLEnum
from enum import Enum
from sqlalchemy.dialects.postgresql import UUID, ENUM
import uuid
from sqlalchemy.orm import declarative_base, mapped_column, Mapped
from src.users.utils import generate_uuid
from datetime import datetime, timezone
from typing import Optional



class UserStatus(str, Enum):
    """User account status."""

    ACTIVE = "active"
    INACTIVE = "inactive"
    

class User(BaseModel):
	"""Users' model for authentication """
	
	__tablename__ = 'users'


	id: Mapped[uuid.UUID] = mapped_column(
		UUID(as_uuid=True),
		default=generate_uuid,
		primary_key=True
		)
	email: Mapped[str] = mapped_column(TEXT, unique=True, nullable=False, index=True)
	name: Mapped[Optional[str]] = mapped_column(TEXT, unique=False, nullable=True)
	mail: Mapped[str] = mapped_column(TEXT, unique=False, nullable=False)
	password_hash: Mapped[str] = mapped_column(TEXT, unique=False, nullable=False)


	refresh_token: Mapped[Optional[str]] = mapped_column(TEXT, unique=False, nullable=True)
	refresh_token_expiration: Mapped[Optional[datetime]] = mapped_column(DateTime(timezone=True), nullable=True)

	email_verification_token: Mapped[Optional[str]] = mapped_column(TEXT, unique=False, nullable=True)
	email_verification_token_expiration: Mapped[Optional[datetime]] = mapped_column(DateTime(timezone=True), nullable=True)
      
	password_reset_token: Mapped[Optional[str]] = mapped_column(TEXT, unique=False, nullable=True)
	password_reset_token_expiration: Mapped[Optional[datetime]] = mapped_column(DateTime(timezone=True), unique=False, nullable=True)
      
	created: Mapped[str] = mapped_column(DateTime(timezone=True), nullable=False, server_default=text("now()"))
	status: Mapped[str] = mapped_column(SQLEnum(UserStatus), name="user_status", nullable=False, default=UserStatus.INACTIVE.value)
      

def is_active(self: User):
	"""Checks if the user is active.
	True = active, False = inactive"""
	return self.status == UserStatus.ACTIVE

def is_email_token_expired(self: User):
	"""Checks if the email verification token has expired.
	True = expired, False = not expired"""
	actual_time = datetime.now(timezone.utc)
	return self.email_verification_token_expiration < actual_time

def is_password_reset_token_expired(self: User):
	"""Checks if the password reset token has expired.
	True = expired, False = not expired"""
	actual_time = datetime.now(timezone.utc)
	return self.password_reset_token_expiration < actual_time

def is_refresh_token_expired(self: User):
	"""Checks if the refresh token has expired.
	True = expired, False = not expired"""
	actual_time = datetime.now(timezone.utc)
	return self.refresh_token_expiration < actual_time