<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/js/jquery-1.11.3.min.js" ></script>
</head>
<body>
<form name="frmMain" method=post>
    <input type="text" id="nameTheme" placeholder="Введите название опроса" value=""><br>
    <div name="Question_0" id="Question_0">
        Выберите вопрос: <input id="txtQuestion_0" type=text value=""><br>
        Выберите тип ответов: <select id="selType_0" value="">
            <option value="1">Текстовый ответ</option>
            <option value="2">Ответ с выбором одного варианта</option>
            <option value="3">Ответ с выбором множества вариантов</option>
        </select><br>
        Если ответ на вопрос опроса не текстовый, напишите перечень ответов используя разделитель "|" (пример: "ответ1|какой-то еще вариант..."): <input type="text" id="variant_0" value="">
        <br>
    </div>
    <input type="button" name="addQuestion" value="Добавить вопрос">
    <input type="button" name="btnCreate" value="Создать">
    <script>
    var cntQuestions = 1;
        function onCreate(ev)
        {
            var listQuestion = new Map();
            var theme =  $("#nameTheme")[0].value;
            var i= 0;
            while(i < 15)
            {
                if($("#Question_"+i).length >= 1)
                {
                    /*var txtQuestion = document.getElementById("txtQuestion_"+i).value,
                    selType = document.getElementById("selType_"+i).value, variant = document.getElementById("variant_"+i).value;*/
                    listQuestion[i] = new Map();
                    listQuestion[i]['txtQuestion'] = $("#txtQuestion_"+i)[0].value;
                    listQuestion[i]['selType'] = $("#selType_"+i)[0].value;
                    listQuestion[i]['variant'] = $("#variant_"+i)[0].value;
                    i++;
                }
                else
                {
                    i++;
                }
            }
            console.log(listQuestion);
            request = {theme : theme, listQuestion : listQuestion};
            $.ajax({
                url: '/createPoll',
                type: 'post',
                contentType:"application/json",
                data: JSON.stringify(request),
                success: function(status, body, data){
                    if(data.responseJSON != "NOT_FOUND")
                    {
                        console.log(data);
                        //$('#message').html(data.responseJSON.name_combo);
                    }
                    else
                    {
                        $('#message').html(data.responseJSON);
                    }
                }
            });
        }

        function addQuestion()
        {
            $("<br> <div id='Question_"+cntQuestions+"'>"+
        "Выберите вопрос: <input id='txtQuestion_"+cntQuestions+"' type=text value=''><br>"+
        "Выберите тип ответов: <select id='selType_"+cntQuestions+"'>"+
            "<option value='1'>Текстовый ответ</option>"+
            "<option value='2'>Ответ с выбором одного варианта</option>"+
            "<option value='3'>Ответ с выбором множества вариантов</option>"+
        "</select><br>"+
        "Если ответ на вопрос опроса не текстовый, напишите перечень ответов используя разделитель '|' (пример: 'ответ1|какой-то еще вариант...'): <input type='text' id='variant_"+cntQuestions+"' value=''>"+
        "<br>"+
    "</div>").
    	   		insertAfter('div[name=Question_'+(cntQuestions-1)+']');
    	   		cntQuestions++;
        }

        window.onload = function()
        {
            frmMain.addQuestion.onclick = addQuestion;
            frmMain.btnCreate.onclick = onCreate;
        }
    </script>
</form>
</body>
</html>