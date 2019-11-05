import json
from flask import Flask, abort, jsonify, request, render_template
from flask_cors import CORS, cross_origin
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

app.config['SECRET_KEY'] = 'SecretKey'
app.config['CORS_HEADERS'] = 'Content-Type'

cors = CORS(app, resources={r"/survival-mode": {"origins": "http://localhost:port"}})

@app.route('/survival-mode', methods = ['GET'])
@cross_origin(origin='localhost',headers=['Content-Type','Authorization'])
def senddata():
    dat = (open("people_in_need.txt", "r"))
    contentArr = []
    for line in dat:
        arr=line.split()
        for word in arr:
            contentArr.append(word)
        # print('\n\n\narr=',arr)
        # tempData=data(arr[0],arr[1],arr[2],arr[3],arr[4])
        # contentArr.append(tempData)
        contentArr.append('%')

        
    dat.close()

    return json.dumps(contentArr)
if __name__ == '__main__':
    app.run(port = 9000, debug=True)
