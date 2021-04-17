FROM golang:latest

RUN mkdir /go/src/sample

RUN go get -u github.com/gorilla/mux

COPY ./main.go /go/src/sample

CMD ["go", "run", "/go/src/sample/main.go"]
