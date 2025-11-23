from typing import TypedDict
from pydantic import BaseModel

class RegistrationResponse(BaseModel):
	access_token: str
	refresh_token: str
	registrationTime: str

class RegistrationRequest(BaseModel):
	username: str
	name: str
	email: str
	password: str

class LoginResponse(BaseModel):
	access_token: str
	refresh_token: str

class LoginRequest(BaseModel):
	username: str
	password: str

