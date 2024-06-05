from fastapi import FastAPI
from fastapi.responses import RedirectResponse
from starlette.responses import PlainTextResponse

from app.config import get_config
from app.model.connector import QueryDryRunError
from app.routers import ibis

app = FastAPI()
app.include_router(ibis.router)


@app.get("/")
def root():
    return RedirectResponse(url="/docs")


@app.get("/health")
def health():
    return {"status": "ok"}


@app.get("/config")
def config():
    return get_config()


@app.exception_handler(QueryDryRunError)
async def query_dry_run_error_handler(request, exc: QueryDryRunError):
    return PlainTextResponse(str(exc), status_code=422)


@app.exception_handler(Exception)
async def exception_handler(request, exc: Exception):
    return PlainTextResponse(str(exc), status_code=500)
