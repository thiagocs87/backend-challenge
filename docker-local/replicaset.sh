#!/bin/sh
set -e

mongo --eval 'rs.initiate({_id : "rs0",members: [{ _id : 0, host : "mongo.me1:27017" },{ _id : 1, host : "mongo.me2:27017" }]})'