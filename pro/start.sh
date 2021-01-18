docker stop eas && docker rm eas && docker rmi eas:latest && docker build -t eas:latest . && docker run -d -p 3332:3332 --name eas eas:latest
