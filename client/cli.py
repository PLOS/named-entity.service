"""Python command line client for NED

Usage:
  cli.py API [METHOD] [<arguments>...]

APIS:
  service
  typeclasses
  individuals
  organizations
  institutionsearch

Examples:
  service config
  typeclasses list
  typeclasses read 12
  individuals find_individuals email emailaddress john@doe.com
  individuals get_address 1 1

Options:
  -h --help     Show this screen.
  --version     Show version.

"""

import swagger_client
import inspect
from docopt import docopt

swagger_client.configuration.username = 'akita'
swagger_client.configuration.password = 'akita'

apiclient = swagger_client.ApiClient('http://localhost:8080/vx', header_name="Authorization", header_value=swagger_client.configuration.get_basic_auth_token())

_method = lambda m, o : getattr(o,m)

if __name__ == '__main__':
    args = docopt(__doc__)

    apiname = args['API']
    method = args['METHOD']
    methodargs = args['<arguments>']
    c = getattr(swagger_client, apiname.capitalize() + "Api")

    # print(args)

    api = c(apiclient)

    if not method:

        members = inspect.getmembers(api, predicate=inspect.ismethod)

        print "Methods:"

        for member in members:
            if member[0].find('__') == 0:
                continue
            m = getattr(c, member[0])
            args = inspect.getargspec(m).args
            del args[0]
            print ("  " + apiname + " " + member[0] + "  " + (' '.join(['<'+x+'>' for x in args])))

    else:
        print(_method(method, api)(*methodargs))
