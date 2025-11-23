from fastapi import FastAPI
from dotenv import load_dotenv
from auth.router import router as auth_router

load_dotenv()

app = FastAPI()

@app.get("/")
async def root():
	return {"message": "Hello World"}

app.include_router(auth_router)