# coding: utf-8

from fastapi.testclient import TestClient


from com.fineos.idam.petinsurance.pet.dto.error import Error  # noqa: F401
from com.fineos.idam.petinsurance.pet.dto.pet import Pet  # noqa: F401


def test_create_pets(client: TestClient):
    """Test case for create_pets

    Create a pet.
    """
    pet = {"name":"name","id":0,"tag":"tag"}

    headers = {
    }
    # uncomment below to make a request
    #response = client.request(
    #    "POST",
    #    "/pets",
    #    headers=headers,
    #    json=pet,
    #)

    # uncomment below to assert the status code of the HTTP response
    #assert response.status_code == 200


def test_list_pets(client: TestClient):
    """Test case for list_pets

    List all pets.
    """
    params = [("limit", 56)]
    headers = {
    }
    # uncomment below to make a request
    #response = client.request(
    #    "GET",
    #    "/pets",
    #    headers=headers,
    #    params=params,
    #)

    # uncomment below to assert the status code of the HTTP response
    #assert response.status_code == 200


def test_show_pet_by_id(client: TestClient):
    """Test case for show_pet_by_id

    Info for a specific pet.
    """

    headers = {
    }
    # uncomment below to make a request
    #response = client.request(
    #    "GET",
    #    "/pets/{petId}".format(petId='pet_id_example'),
    #    headers=headers,
    #)

    # uncomment below to assert the status code of the HTTP response
    #assert response.status_code == 200

