"""Swagger CLI client"""

import sys
import argparse
from pyswagger import SwaggerApp
import urllib

def show_method_list(args):

    app = SwaggerApp._create_(args.spec)

    # TODO: determine url basepath from spec

    methods = []

    for method, op in app.op.items():

        query_params=[]
        for p in op.parameters:
            if getattr(p, 'in') == 'query':
                query_params.append(p.name + "=" + p.type)

        path = op.path + ('?' + '&'.join(query_params) if len(query_params) > 0 else '')

        # rows, cols = os.popen('stty size', 'r').read().split()

        if args.v:
            print (op.summary)
            print ("{:<7}{:<50}".format(op.method.upper(), path))
            print ('Curl sample => ' + get_curl_command(urllib.parse.urljoin(args.spec, op.path[1:]), op.method) + '\n')
        else:
            summary = (op.summary[:15] + '..') if len(op.summary) > 15 else op.summary
            print ("{:<7}{:<50}{:>20}".format(op.method.upper(), path, summary))

def get_curl_command(path, method):

    # TODO: determine if basic auth from spec
    auth = "-u user:pass"
    # TODO: determine model from spec
    model = "{\"foo\": \"bar\"}"

    curl_cmd = 'curl {} -X {} "{}"'.format(auth, method.upper(), path)

    if method == 'post' or method == 'put':
        curl_cmd = "echo '{}' | {} -d @- ".format(model, curl_cmd)

    return (curl_cmd)


def parse_args():

    parser = argparse.ArgumentParser(description='Swagger Doc CLI')
    parser.add_argument('spec', help="Location of swagger spec")
    parser.add_argument('-v', action='store_true', required=False)

    args = parser.parse_args()

    # print (args)

    show_method_list(args)

if __name__ == '__main__':
    parse_args()
