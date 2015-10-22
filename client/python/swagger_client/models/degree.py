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


class Degree(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    """
    def __init__(self):
        """
        Degree - a model defined in Swagger

        :param dict swaggerTypes: The key is attribute name
                                  and the value is attribute type.
        :param dict attributeMap: The key is attribute name
                                  and the value is json key in definition.
        """
        self.swagger_types = {
            'typeid': 'int',
            'type': 'str',
            'source': 'str',
            'nedid': 'int',
            'sourcetypeid': 'int',
            'id': 'int'
        }

        self.attribute_map = {
            'typeid': 'typeid',
            'type': 'type',
            'source': 'source',
            'nedid': 'nedid',
            'sourcetypeid': 'sourcetypeid',
            'id': 'id'
        }

        self._typeid = None
        self._type = None
        self._source = None
        self._nedid = None
        self._sourcetypeid = None
        self._id = None

    @property
    def typeid(self):
        """
        Gets the typeid of this Degree.


        :return: The typeid of this Degree.
        :rtype: int
        """
        return self._typeid

    @typeid.setter
    def typeid(self, typeid):
        """
        Sets the typeid of this Degree.


        :param typeid: The typeid of this Degree.
        :type: int
        """
        self._typeid = typeid

    @property
    def type(self):
        """
        Gets the type of this Degree.


        :return: The type of this Degree.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type):
        """
        Sets the type of this Degree.


        :param type: The type of this Degree.
        :type: str
        """
        self._type = type

    @property
    def source(self):
        """
        Gets the source of this Degree.


        :return: The source of this Degree.
        :rtype: str
        """
        return self._source

    @source.setter
    def source(self, source):
        """
        Sets the source of this Degree.


        :param source: The source of this Degree.
        :type: str
        """
        self._source = source

    @property
    def nedid(self):
        """
        Gets the nedid of this Degree.


        :return: The nedid of this Degree.
        :rtype: int
        """
        return self._nedid

    @nedid.setter
    def nedid(self, nedid):
        """
        Sets the nedid of this Degree.


        :param nedid: The nedid of this Degree.
        :type: int
        """
        self._nedid = nedid

    @property
    def sourcetypeid(self):
        """
        Gets the sourcetypeid of this Degree.


        :return: The sourcetypeid of this Degree.
        :rtype: int
        """
        return self._sourcetypeid

    @sourcetypeid.setter
    def sourcetypeid(self, sourcetypeid):
        """
        Sets the sourcetypeid of this Degree.


        :param sourcetypeid: The sourcetypeid of this Degree.
        :type: int
        """
        self._sourcetypeid = sourcetypeid

    @property
    def id(self):
        """
        Gets the id of this Degree.


        :return: The id of this Degree.
        :rtype: int
        """
        return self._id

    @id.setter
    def id(self, id):
        """
        Sets the id of this Degree.


        :param id: The id of this Degree.
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
