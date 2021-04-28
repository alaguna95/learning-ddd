FROM gradle
RUN mkdir /app
WORKDIR /app
COPY . /app
RUN gradle clean
RUN gradle build
CMD "gradle" "bootRun"
