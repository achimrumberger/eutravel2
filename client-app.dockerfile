FROM nginx:latest
#RUN apt-get update && apt-get install -y iputils-ping
# Copy the build output to replace the default nginx contents.
COPY client/eutravel-client/dist/eutravel-client /usr/share/nginx/html

# Expose port 80
EXPOSE 80