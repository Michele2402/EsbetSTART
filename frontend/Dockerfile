FROM nginx
RUN rm /etc/nginx/conf.d/default.conf && apt update && apt install -y iputils-ping
COPY dist/frontend/browser/ /usr/share/nginx/html/
COPY nginx/default.conf /etc/nginx/conf.d/
EXPOSE 8090/TCP
