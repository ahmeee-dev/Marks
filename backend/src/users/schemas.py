from typing import List, Optional
from .models import User
from pydantic import BaseModel
from datetime import datetime

class UserCreate(BaseModel):
	"""Class used to register a user"""
	email: str
	username: str
	name: str
	password: str
	created_at: datetime

