import json
from flask import Flask, abort, jsonify, request, render_template
#from flask_cors import CORS, cross_origin
import urllib.request

app = Flask(__name__)

class data:
    severity=''
    Latitude=''
    Longitude=''
    FirstName=''
    LastName=''
    
    def __init__(self,severity,Latitude,Longitude,FirstName='',LastName=''):
        self.severity=severity
        self.Latitude=Latitude
        self.Longitude=Longitude
        self.FirstName=FirstName
        self.LastName=LastName

#app.config['SECRET_KEY'] = 'SecretKey'
#app.config['CORS_HEADERS'] = 'Content-Type'

#cors = CORS(app, resources={r"/survival-mode": {"origins": "http://localhost:port"}})

@app.route('/survival-mode', methods = ['GET'])
#@cross_origin(origin='localhost',headers=['Content-Type','Authorization'])
def senddata():
    data = (open("people_in_need.txt", "r"))
    content = data.read()
    content = content.split()
    contentArr = []
    for line in content:
        arr=line.split()
        tempData=data(arr[0],arr[1],arr[2],arr[3],arr[4])
        contentArr.append(tempdata)

        
    data.close()
    return json.dumps(contentArr)

if __name__ == '__main__':
    app.run(port = 9000, debug=True)
