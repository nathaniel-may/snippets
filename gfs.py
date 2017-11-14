from pymongo import MongoClient
import gridfs
from pprint import pprint #prettier printing

client = MongoClient('localhost:27017')
db = MongoClient().gridfs_example
fs = gridfs.GridFS(db)

id = fs.put(b'hello world') #b sends the string as byte type instead of str
pprint(id)

data = fs.get(id).read();
pprint(data);