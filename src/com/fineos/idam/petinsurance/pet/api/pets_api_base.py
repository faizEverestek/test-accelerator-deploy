# coding: utf-8

from typing import ClassVar, Dict, List, Tuple  # noqa: F401

from com.fineos.idam.petinsurance.pet.dto.error import Error
from com.fineos.idam.petinsurance.pet.dto.pet import Pet


class BasePetsApi:
    subclasses: ClassVar[Tuple] = ()

    def __init_subclass__(cls, **kwargs):
        super().__init_subclass__(**kwargs)
        BasePetsApi.subclasses = BasePetsApi.subclasses + (cls,)
    async def create_pets(
        self,
        pet: Pet,
    ) -> None:
        """Create a pet in the store with the given details."""
        ...


    async def list_pets(
        self,
        limit: int,
    ) -> List[Pet]:
        """Get a paged list of pets."""
        ...


    async def show_pet_by_id(
        self,
        petId: str,
    ) -> Pet:
        """Get a specific pet by ID."""
        ...
