#!/usr/bin/env bash
set -ex

make package build-image

if output=$(docker network inspect messagehub); then
    echo "Docker network already exists"
else
    echo "Creating docker network..."
    docker network create -d bridge messagehub
fi

docker-compose up -d

trap 'echo "Stopping docker"; docker-compose down' SIGINT

echo "environment ready"

docker-compose logs -f qa-message-hub