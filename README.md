# my-java-application

1)	It’s a Spring boot Application (I have used STS to develop the application).
2)	Used Maven for the dependencies (So might take some time to download dependencies on your local)
3)	If you want to test, just run the starter class and use command prompt to run your curl commands.

4) There was some issues with the request shared, so have updated the requests

curl http://localhost:8080/counter-api/search -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -H"Content-Type: application/json" -d"{\"searchText\":[\"Duis\", \"Sed\", \"Donec\", \"Augue\", \"Pellentesque\", \"123\"]}" –X POST

curl http://localhost:8080/counter-api/top/20 -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -H"Accept: text/csv"
