console.log("fromto.js 확인");

document.addEventListener("DOMContentLoaded", function() {
    const fromTitleNum = document.getElementById("fromTitleNum");
    const fromChapterNum = document.getElementById("fromChapterNum");
    const fromVerseNum = document.getElementById("fromVerseNum");
    const toTitleNum = document.getElementById("toTitleNum");
    const toChapterNum = document.getElementById("toChapterNum");
    const toVerseNum = document.getElementById("toVerseNum");
    const btnCheck = document.getElementById("btn-check");
    const tbody = document.getElementById("data-table");

    btnCheck.addEventListener("click", function() {
        const body = {
            fromTitleNum: fromTitleNum.value.trim(),
            fromChapterNum: parseInt(fromChapterNum.value.trim()),
            fromVerseNum: parseInt(fromVerseNum.value.trim()),
            toTitleNum: toTitleNum.value.trim(),
            toChapterNum: parseInt(toChapterNum.value.trim()),
            toVerseNum: parseInt(toVerseNum.value.trim())
        };


        if (!body.fromTitleNum || isNaN(body.fromChapterNum) || isNaN(body.fromVerseNum) ||
            !body.toTitleNum || isNaN(body.toChapterNum) || isNaN(body.toVerseNum)) {
            alert("모든 입력값을 올바르게 입력해주세요.");
            return;
        }


        fetch("http://localhost:8080/content/fromTo", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(body)
        })
        .then(response => response.json())
        .then(data => {
            tbody.innerHTML = "";

            data.forEach(item => {
                const tr = document.createElement("tr");

                const tdTitle = document.createElement("td");
                tdTitle.textContent = item.title;
                tr.appendChild(tdTitle);

                const tdTitleNm = document.createElement("td");
                tdTitleNm.textContent = item.titleNm;
                tr.appendChild(tdTitleNm);

                const tdChapter = document.createElement("td");
                tdChapter.textContent = item.chapter;
                tr.appendChild(tdChapter);

                const tdChapterNm = document.createElement("td");
                tdChapterNm.textContent = item.chapterNm;
                tr.appendChild(tdChapterNm);

                const tdVerse = document.createElement("td");
                tdVerse.textContent = item.verse;
                tr.appendChild(tdVerse);

                const tdVerseNm = document.createElement("td");
                tdVerseNm.textContent = item.verseNm;
                tr.appendChild(tdVerseNm);

                const tdObject = document.createElement("td");
                tdObject.textContent = item.object;
                tr.appendChild(tdObject);

                tbody.appendChild(tr);
            });
        })
        .catch(err => {
            console.error("요청 실패:", err);
            tbody.innerHTML = "<tr><td colspan='7'>데이터 로딩 실패</td></tr>";
        });
    });
});