<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/js/jquery-1.11.3.min.js" ></script>
</head>
<body>
<form name="frmMain" method="post">
    <input type="hidden" name="idTheme" value="${id}">
    <#list poll as el>
    <div id="responseOption_${el.getId()}">
        <b>${el.text}</b><br>
        <#if el.getQuestionType().ordinal() == 0>
        <div seltype="${el.getQuestionType().ordinal()}" id="variant_${el.getId()}">
            <input type="text" value="">
        </div>
        <#elseif el.getQuestionType().ordinal() == 1>
        <div seltype="${el.getQuestionType().ordinal()}" id="variant_${el.getId()}">
            <#list el.getOptions() as option>
        <p><input type="radio"> ${option.getText()}</p>
            </#list>
        </div>
        <#else>
            <div seltype="${el.getQuestionType().ordinal()}" id="variant_${el.getId()}">
                <#list el.getOptions() as option>
                <p><input type="checkbox"> ${option.getText()}</p>
            </#list>
        </div>
        </#if>
    </div>
    </#list><br>
<input type="button" name="btnResp" id="" value="Ответить">
<script>
    var cntQuestions = 1;
        function onSave(ev)
        {
            var listQuestion = new Map();
            var i= 0;
            var idTheme = frmMain.idTheme.value;
            while(i < 15)
            {
                if($("#responseOption_"+i).length >= 1)
                {
                console.log();
                var seltype = $("#variant_"+i)[0].getAttribute("seltype"),
                arResponse = $("#variant_"+i)[0].children
                    listQuestion[i] = new Map();
                    listQuestion[i]['variant'] = "";
                    listQuestion[i]['selType'] = seltype;
                    if(arResponse.length > 1)
                    {
                        for(var j = 0; j < arResponse.length; j++)
                        {
                            console.log(arResponse);
                            if(arResponse[j].firstElementChild.checked)
                            {
                                listQuestion[i]['variant'] += arResponse[j].textContent+";";
                            }
                        }
                    }
                    else
                    {
                        listQuestion[i]['variant'] = arResponse[0].value;
                    }
                    i++;
                }
                else
                {
                    i++;
                }
            }
            console.log(listQuestion);
            request = {id : idTheme, listQuestion : listQuestion};
            $.ajax({
                url: '/saveResponse',
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

        window.onload = function()
        {
            frmMain.btnResp.onclick = onSave;
        }
</script>
</form>
</body>
</html>