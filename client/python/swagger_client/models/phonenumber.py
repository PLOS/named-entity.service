# coding: utf-8

"""
Copyright 2015 SmartBear Software

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

    Ref: https://github.com/swagger-api/swagger-codegen
"""

from pprint import pformat
from six import iteritems


class Phonenumber(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    """
    def __init__(self):
        """
        Phonenumber - a model defined in Swagger

        :param dict swaggerTypes: The key is attribute name
                                  and the value is attribute type.
        :param dict attributeMap: The key is attribute name
                                  and the value is json key in definition.
        """
        self.swagger_types = {
            'typeid': 'int',
            'type': 'str',
            'countrycodetypeid': 'int',
            'countrycodetype': 'str',
            'phonenumber': 'str',
            'extension': 'str',
            'isactive': 'bool',
            'source': 'str',
            'nedid': 'int',
            'sourcetypeid': 'int',
            'id': 'int'
        }

        self.attribute_map = {
            'typeid': 'typeid',
            'type': 'type',
            'countrycodetypeid': 'countrycodetypeid',
            'countrycodetype': 'countrycodetype',
            'phonenumber': 'phonenumber',
            'extension': 'extension',
            'isactive': 'isactive',
            'source': 'source',
            'nedid': 'nedid',
            'sourcetypeid': 'sourcetypeid',
            'id': 'id'
        }

        self._typeid = None
        self._type = None
        self._countrycodetypeid = None
        self._countrycodetype = None
        self._phonenumber = None
        self._extension = None
        self._isactive = None
        self._source = None
        self._nedid = None
        self._sourcetypeid = None
        self._id = None

    @property
    def typeid(self):
        """
        Gets the typeid of this Phonenumber.


        :return: The typeid of this Phonenumber.
        :rtype: int
        """
        return self._typeid

    @typeid.setter
    def typeid(self, typeid):
        """
        Sets the typeid of this Phonenumber.


        :param typeid: The typeid of this Phonenumber.
        :type: int
        """
        self._typeid = typeid

    @property
    def type(self):
        """
        Gets the type of this Phonenumber.


        :return: The type of this Phonenumber.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type):
        """
        Sets the type of this Phonenumber.


        :param type: The type of this Phonenumber.
        :type: str
        """
        self._type = type

    @property
    def countrycodetypeid(self):
        """
        Gets the countrycodetypeid of this Phonenumber.


        :return: The countrycodetypeid of this Phonenumber.
        :rtype: int
        """
        return self._countrycodetypeid

    @countrycodetypeid.setter
    def countrycodetypeid(self, countrycodetypeid):
        """
        Sets the countrycodetypeid of this Phonenumber.


        :param countrycodetypeid: The countrycodetypeid of this Phonenumber.
        :type: int
        """
        self._countrycodetypeid = countrycodetypeid

    @property
    def countrycodetype(self):
        """
        Gets the countrycodetype of this Phonenumber.


        :return: The countrycodetype of this Phonenumber.
        :rtype: str
        """
        return self._countrycodetype

    @countrycodetype.setter
    def countrycodetype(self, countrycodetype):
        """
        Sets the countrycodetype of this Phonenumber.


        :param countrycodetype: The countrycodetype of this Phonenumber.
        :type: str
        """
        self._countrycodetype = countrycodetype

    @property
    def phonenumber(self):
        """
        Gets the phonenumber of this Phonenumber.


        :return: The phonenumber of this Phonenumber.
        :rtype: str
        """
        return self._phonenumber

    @phonenumber.setter
    def phonenumber(self, phonenumber):
        """
        Sets the phonenumber of this Phonenumber.


        :param phonenumber: The phonenumber of this Phonenumber.
        :type: str
        """
        self._phonenumber = phonenumber

    @property
    def extension(self):
        """
        Gets the extension of this Phonenumber.


        :return: The extension of this Phonenumber.
        :rtype: str
        """
        return self._extension

    @extension.setter
    def extension(self, extension):
        """
        Sets the extension of this Phonenumber.


        :param extension: The extension of this Phonenumber.
        :type: str
        """
        self._extension = extension

    @property
    def isactive(self):
        """
        Gets the isactive of this Phonenumber.


        :return: The isactive of this Phonenumber.
        :rtype: bool
        """
        return self._isactive

    @isactive.setter
    def isactive(self, isactive):
        """
        Sets the isactive of this Phonenumber.


        :param isactive: The isactive of this Phonenumber.
        :type: bool
        """
        self._isactive = isactive

    @property
    def source(self):
        """
        Gets the source of this Phonenumber.


        :return: The source of this Phonenumber.
        :rtype: str
        """
        return self._source

    @source.setter
    def source(self, source):
        """
        Sets the source of this Phonenumber.


        :param source: The source of this Phonenumber.
        :type: str
        """
        self._source = source

    @property
    def nedid(self):
        """
        Gets the nedid of this Phonenumber.


        :return: The nedid of this Phonenumber.
        :rtype: int
        """
        return self._nedid

    @nedid.setter
    def nedid(self, nedid):
        """
        Sets the nedid of this Phonenumber.


        :param nedid: The nedid of this Phonenumber.
        :type: int
        """
        self._nedid = nedid

    @property
    def sourcetypeid(self):
        """
        Gets the sourcetypeid of this Phonenumber.


        :return: The sourcetypeid of this Phonenumber.
        :rtype: int
        """
        return self._sourcetypeid

    @sourcetypeid.setter
    def sourcetypeid(self, sourcetypeid):
        """
        Sets the sourcetypeid of this Phonenumber.


        :param sourcetypeid: The sourcetypeid of this Phonenumber.
        :type: int
        """
        self._sourcetypeid = sourcetypeid

    @property
    def id(self):
        """
        Gets the id of this Phonenumber.


        :return: The id of this Phonenumber.
        :rtype: int
        """
        return self._id

    @id.setter
    def id(self, id):
        """
        Sets the id of this Phonenumber.


        :param id: The id of this Phonenumber.
        :type: int
        """
        self._id = id

    def to_dict(self):
        """
        Returns the model properties as a dict
        """
        result = {}

        for attr, _ in iteritems(self.swagger_types):
            value = getattr(self, attr)
            if isinstance(value, list):
                result[attr] = list(map(
                    lambda x: x.to_dict() if hasattr(x, "to_dict") else x,
                    value
                ))
            elif hasattr(value, "to_dict"):
                result[attr] = value.to_dict()
            else:
                result[attr] = value

        return result

    def to_str(self):
        """
        Returns the string representation of the model
        """
        return pformat(self.to_dict())

    def __repr__(self):
        """
        For `print` and `pprint`
        """
        return self.to_str()