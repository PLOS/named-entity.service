"""Python command line client for NED

Usage:
  cli.py [-i FILE] API [METHOD] [<arguments>...]

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
  individuals create_individual -i composite.json

Options:
  -i FILE       The input file (or use '-' for stdin)
  -h --help     Show this screen.
"""

import ned_client
import inspect
import sys
from docopt import docopt

ned_client.configuration.username = 'akita'
ned_client.configuration.password = 'akita'
# ned_client.configuration.debug = True

apiclient = ned_client.ApiClient('http://localhost:8080/v0')

_method = lambda m, o : getattr(o,m)

if __name__ == '__main__':
    args = docopt(__doc__)

    apiname = args['API']
    method = args['METHOD']
    methodargs = args['<arguments>']
    infile = args["-i"]

    c = getattr(ned_client, apiname.capitalize() + "Api")

    # print(args)

    api = c(apiclient)

    if not method:

        members = inspect.getmembers(api, predicate=inspect.ismethod)

        # print "Methods:"

        for member in members:
            if member[0].find('__') == 0:
                continue
            m = getattr(c, member[0])
            args = inspect.getargspec(m).args
            del args[0]
            # print ("  " + apiname + " " + member[0] + "  " + (' '.join(['<'+x+'>' for x in args])))

    else:
        if infile:
            if infile == '-':
                fh = sys.stdin
            else:
                fh = open(infile, "r")
            body = fh.read()
            # print (body)
            fh.close()
            print(_method(method, api)(*methodargs, body=body))
        else:
            print(_method(method, api)(*methodargs))
