# coding: utf-8

from typing import Dict, List  # noqa: F401
import importlib
import pkgutil

from com.fineos.idam.petinsurance.pet.api.pets_api_base import BasePetsApi
import com.fineos.idam.petinsurance.pet.impl

from fastapi import (  # noqa: F401
    APIRouter,
    Body,
    Cookie,
    Depends,
    Form,
    Header,
    HTTPException,
    Path,
    Query,
    Response,
    Security,
    status,
)

from com.fineos.idam.petinsurance.pet.dto.extra_models import TokenModel  # noqa: F401
from com.fineos.idam.petinsurance.pet.dto.error import Error
from com.fineos.idam.petinsurance.pet.dto.pet import Pet


router = APIRouter()

ns_pkg = com.fineos.idam.petinsurance.pet.impl
for _, name, _ in pkgutil.iter_modules(ns_pkg.__path__, ns_pkg.__name__ + "."):
    importlib.import_module(name)


@router.post(
    "/pets",
    responses={
        201: {"description": "Null response."},
        200: {"model": Error, "description": "unexpected error,"},
    },
    tags=["pets"],
    summary="Create a pet.",
    response_model_by_alias=True,
)
async def create_pets(
    pet: Pet = Body(None, description="Pet object that needs to be added to the store."),
) -> None:
    """Create a pet in the store with the given details."""
    if not BasePetsApi.subclasses:
        raise HTTPException(status_code=501, detail="Not implemented")

    if len(BasePetsApi.subclasses) > 1:
        raise HTTPException(status_code=500, detail="Multiple implementations found, You can have only one implementation for the base class")
            
    return await BasePetsApi.subclasses[0]().create_pets(pet)


@router.get(
    "/pets",
    responses={
        200: {"model": List[Pet], "description": "A paged array of pets."},
        200: {"model": Error, "description": "unexpected error."},
    },
    tags=["pets"],
    summary="List all pets.",
    response_model_by_alias=True,
)
async def list_pets(
    limit: int = Query(None, description="How many items to return at one time (max 100).", alias="limit"),
) -> List[Pet]:
    """Get a paged list of pets."""
    if not BasePetsApi.subclasses:
        raise HTTPException(status_code=501, detail="Not implemented")

    if len(BasePetsApi.subclasses) > 1:
        raise HTTPException(status_code=500, detail="Multiple implementations found, You can have only one implementation for the base class")
            
    return await BasePetsApi.subclasses[0]().list_pets(limit)


@router.get(
    "/pets/{petId}",
    responses={
        200: {"model": Pet, "description": "Expected response to a valid request."},
        200: {"model": Error, "description": "unexpected error."},
    },
    tags=["pets"],
    summary="Info for a specific pet.",
    response_model_by_alias=True,
)
async def show_pet_by_id(
    petId: str = Path(..., description="The id of the pet to retrieve."),
) -> Pet:
    """Get a specific pet by ID."""
    if not BasePetsApi.subclasses:
        raise HTTPException(status_code=501, detail="Not implemented")

    if len(BasePetsApi.subclasses) > 1:
        raise HTTPException(status_code=500, detail="Multiple implementations found, You can have only one implementation for the base class")
            
    return await BasePetsApi.subclasses[0]().show_pet_by_id(petId)
