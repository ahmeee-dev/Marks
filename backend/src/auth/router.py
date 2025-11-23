from fastapi import APIRouter, HTTPException, Depends
from .schemas import RegistrationRequest, RegistrationResponse, LoginResponse, LoginRequest, EmailVerificationResponse
from sqlalchemy.orm import Session
from sqlalchemy.ext.asyncio import AsyncSession
from src.users.models import User
from src.users.schemas import UserCreate
from src.utils.utils import get_db_async
from .service import AuthService, get_auth_service

router = APIRouter(prefix="/auth", tags=["auth"])

#Mock
sessionLocal: Session


@router.post("/register", response_model=RegistrationResponse)
async def register(payload: RegistrationRequest, session: AsyncSession = Depends(get_db_async), service: AuthService = Depends(get_auth_service)) -> EmailVerificationResponse:
	"""Registers user"""
	result = await service.register(
		session=session, email=payload.email, password=payload.password
	)
	return EmailVerificationResponse(
		messages=result["message"], user_id=result["user_id"]
	)


@router.post("/login", response_model=LoginResponse)
def login(request: LoginRequest):
	# semplice login
	#query db
	raise HTTPException(status_code=400, detail="Not implemented")


@router.post("/logout")	
def logout():
	# semplice logout
	#query db
	raise HTTPException(status_code=400, detail="Not implemented")

@router.post("/refresh")
def refresh():
	# semplice refresh
	#query db
	raise HTTPException(status_code=400, detail="Not implemented")

