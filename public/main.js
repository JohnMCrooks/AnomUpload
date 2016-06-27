function getFiles(data) {
    for (var i in data){

        var elem = $("<a>");
        elem.attr("href", "files/" + data[i].realFileName);
        elem.text("File # " + data[i].id + " - " + data[i].description);

        $("#list").append(elem);
        $("#list").append($("<br>"));

    }
}

$.get("/files", getFiles)

