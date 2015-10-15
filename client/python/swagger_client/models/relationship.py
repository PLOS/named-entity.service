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


class Relationship(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    """
    def __init__(self):
        """
        Relationship - a model defined in Swagger

        :param dict swaggerTypes: The key is attribute name
                                  and the value is attribute type.
        :param dict attributeMap: The key is attribute name
                                  and the value is json key in definition.
        """
        self.swagger_types = {
            'nedidrelated': 'int',
            'typeid': 'int',
            'type': 'str',
            'title': 'str',
            'startdate': 'datetime',
            'enddate': 'datetime',
            'source': 'str',
            'nedid': 'int',
            'sourcetypeid': 'int',
            'id': 'int'
        }

        self.attribute_map = {
            'nedidrelated': 'nedidrelated',
            'typeid': 'typeid',
            'type': 'type',
            'title': 'title',
            'startdate': 'startdate',
            'enddate': 'enddate',
            'source': 'source',
            'nedid': 'nedid',
            'sourcetypeid': 'sourcetypeid',
            'id': 'id'
        }

        self._nedidrelated = None
        self._typeid = None
        self._type = None
        self._title = None
        self._startdate = None
        self._enddate = None
        self._source = None
        self._nedid = None
        self._sourcetypeid = None
        self._id = None

    @property
    def nedidrelated(self):
        """
        Gets the nedidrelated of this Relationship.


        :return: The nedidrelated of this Relationship.
        :rtype: int
        """
        return self._nedidrelated

    @nedidrelated.setter
    def nedidrelated(self, nedidrelated):
        """
        Sets the nedidrelated of this Relationship.


        :param nedidrelated: The nedidrelated of this Relationship.
        :type: int
        """
        self._nedidrelated = nedidrelated

    @property
    def typeid(self):
        """
        Gets the typeid of this Relationship.


        :return: The typeid of this Relationship.
        :rtype: int
        """
        return self._typeid

    @typeid.setter
    def typeid(self, typeid):
        """
        Sets the typeid of this Relationship.


        :param typeid: The typeid of this Relationship.
        :type: int
        """
        self._typeid = typeid

    @property
    def type(self):
        """
        Gets the type of this Relationship.


        :return: The type of this Relationship.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type):
        """
        Sets the type of this Relationship.


        :param type: The type of this Relationship.
        :type: str
        """
        self._type = type

    @property
    def title(self):
        """
        Gets the title of this Relationship.


        :return: The title of this Relationship.
        :rtype: str
        """
        return self._title

    @title.setter
    def title(self, title):
        """
        Sets the title of this Relationship.


        :param title: The title of this Relationship.
        :type: str
        """
        self._title = title

    @property
    def startdate(self):
        """
        Gets the startdate of this Relationship.


        :return: The startdate of this Relationship.
        :rtype: datetime
        """
        return self._startdate

    @startdate.setter
    def startdate(self, startdate):
        """
        Sets the startdate of this Relationship.


        :param startdate: The startdate of this Relationship.
        :type: datetime
        """
        self._startdate = startdate

    @property
    def enddate(self):
        """
        Gets the enddate of this Relationship.


        :return: The enddate of this Relationship.
        :rtype: datetime
        """
        return self._enddate

    @enddate.setter
    def enddate(self, enddate):
        """
        Sets the enddate of this Relationship.


        :param enddate: The enddate of this Relationship.
        :type: datetime
        """
        self._enddate = enddate

    @property
    def source(self):
        """
        Gets the source of this Relationship.


        :return: The source of this Relationship.
        :rtype: str
        """
        return self._source

    @source.setter
    def source(self, source):
        """
        Sets the source of this Relationship.


        :param source: The source of this Relationship.
        :type: str
        """
        self._source = source

    @property
    def nedid(self):
        """
        Gets the nedid of this Relationship.


        :return: The nedid of this Relationship.
        :rtype: int
        """
        return self._nedid

    @nedid.setter
    def nedid(self, nedid):
        """
        Sets the nedid of this Relationship.


        :param nedid: The nedid of this Relationship.
        :type: int
        """
        self._nedid = nedid

    @property
    def sourcetypeid(self):
        """
        Gets the sourcetypeid of this Relationship.


        :return: The sourcetypeid of this Relationship.
        :rtype: int
        """
        return self._sourcetypeid

    @sourcetypeid.setter
    def sourcetypeid(self, sourcetypeid):
        """
        Sets the sourcetypeid of this Relationship.


        :param sourcetypeid: The sourcetypeid of this Relationship.
        :type: int
        """
        self._sourcetypeid = sourcetypeid

    @property
    def id(self):
        """
        Gets the id of this Relationship.


        :return: The id of this Relationship.
        :rtype: int
        """
        return self._id

    @id.setter
    def id(self, id):
        """
        Sets the id of this Relationship.


        :param id: The id of this Relationship.
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
