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


class OrganizationComposite(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    """
    def __init__(self):
        """
        OrganizationComposite - a model defined in Swagger

        :param dict swaggerTypes: The key is attribute name
                                  and the value is attribute type.
        :param dict attributeMap: The key is attribute name
                                  and the value is json key in definition.
        """
        self.swagger_types = {
            'addresses': 'list[Address]',
            'emails': 'list[Email]',
            'phonenumbers': 'list[Phonenumber]',
            'uniqueidentifiers': 'list[Uniqueidentifier]',
            'created': 'datetime',
            'type': 'str',
            'source': 'str',
            'sourcetypeid': 'int',
            'nedid': 'int',
            'typeid': 'int',
            'isactive': 'bool',
            'lastmodified': 'datetime',
            'familiarname': 'str',
            'legalname': 'str',
            'maincontactid': 'int',
            'type_name': 'str'
        }

        self.attribute_map = {
            'addresses': 'addresses',
            'emails': 'emails',
            'phonenumbers': 'phonenumbers',
            'uniqueidentifiers': 'uniqueidentifiers',
            'created': 'created',
            'type': 'type',
            'source': 'source',
            'sourcetypeid': 'sourcetypeid',
            'nedid': 'nedid',
            'typeid': 'typeid',
            'isactive': 'isactive',
            'lastmodified': 'lastmodified',
            'familiarname': 'familiarname',
            'legalname': 'legalname',
            'maincontactid': 'maincontactid',
            'type_name': 'typeName'
        }

        self._addresses = None
        self._emails = None
        self._phonenumbers = None
        self._uniqueidentifiers = None
        self._created = None
        self._type = None
        self._source = None
        self._sourcetypeid = None
        self._nedid = None
        self._typeid = None
        self._isactive = False
        self._lastmodified = None
        self._familiarname = None
        self._legalname = None
        self._maincontactid = None
        self._type_name = None

    @property
    def addresses(self):
        """
        Gets the addresses of this OrganizationComposite.


        :return: The addresses of this OrganizationComposite.
        :rtype: list[Address]
        """
        return self._addresses

    @addresses.setter
    def addresses(self, addresses):
        """
        Sets the addresses of this OrganizationComposite.


        :param addresses: The addresses of this OrganizationComposite.
        :type: list[Address]
        """
        self._addresses = addresses

    @property
    def emails(self):
        """
        Gets the emails of this OrganizationComposite.


        :return: The emails of this OrganizationComposite.
        :rtype: list[Email]
        """
        return self._emails

    @emails.setter
    def emails(self, emails):
        """
        Sets the emails of this OrganizationComposite.


        :param emails: The emails of this OrganizationComposite.
        :type: list[Email]
        """
        self._emails = emails

    @property
    def phonenumbers(self):
        """
        Gets the phonenumbers of this OrganizationComposite.


        :return: The phonenumbers of this OrganizationComposite.
        :rtype: list[Phonenumber]
        """
        return self._phonenumbers

    @phonenumbers.setter
    def phonenumbers(self, phonenumbers):
        """
        Sets the phonenumbers of this OrganizationComposite.


        :param phonenumbers: The phonenumbers of this OrganizationComposite.
        :type: list[Phonenumber]
        """
        self._phonenumbers = phonenumbers

    @property
    def uniqueidentifiers(self):
        """
        Gets the uniqueidentifiers of this OrganizationComposite.


        :return: The uniqueidentifiers of this OrganizationComposite.
        :rtype: list[Uniqueidentifier]
        """
        return self._uniqueidentifiers

    @uniqueidentifiers.setter
    def uniqueidentifiers(self, uniqueidentifiers):
        """
        Sets the uniqueidentifiers of this OrganizationComposite.


        :param uniqueidentifiers: The uniqueidentifiers of this OrganizationComposite.
        :type: list[Uniqueidentifier]
        """
        self._uniqueidentifiers = uniqueidentifiers

    @property
    def created(self):
        """
        Gets the created of this OrganizationComposite.


        :return: The created of this OrganizationComposite.
        :rtype: datetime
        """
        return self._created

    @created.setter
    def created(self, created):
        """
        Sets the created of this OrganizationComposite.


        :param created: The created of this OrganizationComposite.
        :type: datetime
        """
        self._created = created

    @property
    def type(self):
        """
        Gets the type of this OrganizationComposite.


        :return: The type of this OrganizationComposite.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type):
        """
        Sets the type of this OrganizationComposite.


        :param type: The type of this OrganizationComposite.
        :type: str
        """
        self._type = type

    @property
    def source(self):
        """
        Gets the source of this OrganizationComposite.


        :return: The source of this OrganizationComposite.
        :rtype: str
        """
        return self._source

    @source.setter
    def source(self, source):
        """
        Sets the source of this OrganizationComposite.


        :param source: The source of this OrganizationComposite.
        :type: str
        """
        self._source = source

    @property
    def sourcetypeid(self):
        """
        Gets the sourcetypeid of this OrganizationComposite.


        :return: The sourcetypeid of this OrganizationComposite.
        :rtype: int
        """
        return self._sourcetypeid

    @sourcetypeid.setter
    def sourcetypeid(self, sourcetypeid):
        """
        Sets the sourcetypeid of this OrganizationComposite.


        :param sourcetypeid: The sourcetypeid of this OrganizationComposite.
        :type: int
        """
        self._sourcetypeid = sourcetypeid

    @property
    def nedid(self):
        """
        Gets the nedid of this OrganizationComposite.


        :return: The nedid of this OrganizationComposite.
        :rtype: int
        """
        return self._nedid

    @nedid.setter
    def nedid(self, nedid):
        """
        Sets the nedid of this OrganizationComposite.


        :param nedid: The nedid of this OrganizationComposite.
        :type: int
        """
        self._nedid = nedid

    @property
    def typeid(self):
        """
        Gets the typeid of this OrganizationComposite.


        :return: The typeid of this OrganizationComposite.
        :rtype: int
        """
        return self._typeid

    @typeid.setter
    def typeid(self, typeid):
        """
        Sets the typeid of this OrganizationComposite.


        :param typeid: The typeid of this OrganizationComposite.
        :type: int
        """
        self._typeid = typeid

    @property
    def isactive(self):
        """
        Gets the isactive of this OrganizationComposite.


        :return: The isactive of this OrganizationComposite.
        :rtype: bool
        """
        return self._isactive

    @isactive.setter
    def isactive(self, isactive):
        """
        Sets the isactive of this OrganizationComposite.


        :param isactive: The isactive of this OrganizationComposite.
        :type: bool
        """
        self._isactive = isactive

    @property
    def lastmodified(self):
        """
        Gets the lastmodified of this OrganizationComposite.


        :return: The lastmodified of this OrganizationComposite.
        :rtype: datetime
        """
        return self._lastmodified

    @lastmodified.setter
    def lastmodified(self, lastmodified):
        """
        Sets the lastmodified of this OrganizationComposite.


        :param lastmodified: The lastmodified of this OrganizationComposite.
        :type: datetime
        """
        self._lastmodified = lastmodified

    @property
    def familiarname(self):
        """
        Gets the familiarname of this OrganizationComposite.


        :return: The familiarname of this OrganizationComposite.
        :rtype: str
        """
        return self._familiarname

    @familiarname.setter
    def familiarname(self, familiarname):
        """
        Sets the familiarname of this OrganizationComposite.


        :param familiarname: The familiarname of this OrganizationComposite.
        :type: str
        """
        self._familiarname = familiarname

    @property
    def legalname(self):
        """
        Gets the legalname of this OrganizationComposite.


        :return: The legalname of this OrganizationComposite.
        :rtype: str
        """
        return self._legalname

    @legalname.setter
    def legalname(self, legalname):
        """
        Sets the legalname of this OrganizationComposite.


        :param legalname: The legalname of this OrganizationComposite.
        :type: str
        """
        self._legalname = legalname

    @property
    def maincontactid(self):
        """
        Gets the maincontactid of this OrganizationComposite.


        :return: The maincontactid of this OrganizationComposite.
        :rtype: int
        """
        return self._maincontactid

    @maincontactid.setter
    def maincontactid(self, maincontactid):
        """
        Sets the maincontactid of this OrganizationComposite.


        :param maincontactid: The maincontactid of this OrganizationComposite.
        :type: int
        """
        self._maincontactid = maincontactid

    @property
    def type_name(self):
        """
        Gets the type_name of this OrganizationComposite.


        :return: The type_name of this OrganizationComposite.
        :rtype: str
        """
        return self._type_name

    @type_name.setter
    def type_name(self, type_name):
        """
        Sets the type_name of this OrganizationComposite.


        :param type_name: The type_name of this OrganizationComposite.
        :type: str
        """
        allowed_values = ["INDIVIDUAL", "ORGANIZATION", "INVALID_NAMEDPARTY_TYPE"]
        if type_name not in allowed_values:
            raise ValueError(
                "Invalid value for `type_name`, must be one of {0}"
                .format(allowed_values)
            )
        self._type_name = type_name

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

    def __eq__(self, other): 
        """
        Returns true if both objects are equal
        """
        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """ 
        Returns true if both objects are not equal
        """
        return not self == other

