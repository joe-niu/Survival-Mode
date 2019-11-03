import json
from flask import Flask, abort, jsonify, request, render_template
#from flask_cors import CORS, cross_origin
import urllib.request

app = Flask(__name__)

#app.config['SECRET_KEY'] = 'SecretKey'
#app.config['CORS_HEADERS'] = 'Content-Type'

#cors = CORS(app, resources={r"/survival-mode": {"origins": "http://localhost:port"}})

@app.route('/survival-mode', methods = ['GET'])
#@cross_origin(origin='localhost',headers=['Content-Type','Authorization'])
def senddata():
    data = (open("people_in_need.txt", "r"))
    content = data.read()
    content = content.split()
    data.close()
    return {'Longitude1' : content[0],
            'Latitude1' : content[1],
            'Severity1' : content[2],
            'Longitude2' : content[3],
            'Latitude2' : content[4],
            'Severity2' : content[5],
            'Longitude3' : content[6],
            'Latitude3' : content[7],
            'Severity3' : content[8],}

if __name__ == '__main__':
    app.run(port = 9000, debug=True)
