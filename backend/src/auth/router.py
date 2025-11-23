from fastapi import APIRouter, HTTPException, Depends
from .schemas import RegistrationRequest, RegistrationResponse, LoginResponse, LoginRequest
from sqlalchemy.orm import Session
from sqlalchemy.ext.asyncio import AsyncSession
from src.users.models import User
from src.users.schemas import UserCreate
from src.utils.utils import get_db_async
from .service import registration_service

router = APIRouter(prefix="/auth", tags=["auth"])

#Mock
sessionLocal: Session


@router.post("/register", response_model=RegistrationResponse)
def register(request: RegistrationRequest, session: AsyncSession = get_db_async()):
	#query db
	raise HTTPException(status_code=400, detail="Not implemented")


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

