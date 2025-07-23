from http import HTTPStatus
import toml
from com.everestek.hub.evhub.api.management_api_base import BaseManagementApi

class ManagementApiImpl(BaseManagementApi):
    async def health_check(self) -> None:
        """returns the service status"""
        return HTTPStatus.OK

    async def get_version(self) -> str:
        """Read the version from pyproject.toml"""
        with open("pyproject.toml", 'r') as file:
            pyproject_data = toml.load(file)
            version = pyproject_data.get('project', {}).get('version', 'unknown')
            return version