var student_list;
function checkFormDatabase(id){
	if(id.length <10){
		return;
	}
	var action = "../iyf/studentByMobileNo/?mobileNumber=" + id;
	 
	console.log("alert is working  "+action);
	$.ajax({url: action,type:"GET",async:false,dataType:"json" ,success:function(data) {
			result = viewSingleDtls(data,1);
		},error: function(errorMsg,textstatus,errorthrown) {
			alert('getStudenInfo '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		}
	});
}

function viewSingleDtls(res, mode) {
	
	student_list=res;
	var parsedData = res;
	console.log(student_list);
	var temp = "<option value=''>New </option>";
	for (var i = 0; i < parsedData.length; i++) {
		temp += "<option value='" + parsedData[i].stStudentId + "'>"
			+ parsedData[i].firstName+" "+ parsedData[i].lastName+ " ( "+parsedData[i].stStudentId +" )"+" </option>";
	}
	$('#registrationId').html(temp);
	setTimeout(function() {
		$("#wait").hide();
	}, 500);
	
	//var parsedData = JSON.parse(res);
	console.log("========"+parsedData);
	console.log(res);
	if(res.length >0){
		$('#stOccupation').val(res[0].stOccupation);
		$('#userName').val(res[0].firstName);
		$('#lastName').val(res[0].lastName);
		$('#email').val(res[0].email);
		$('#mChanting').val(res[0].mChanting);
		$('#stAddress').val(res[0].stAddress);
		$('#registrationId').val(res[0].stStudentId);
	}else{
		$('#stOccupation').val('');
		$('#userName').val('');
		$('#lastName').val('');
		$('#email').val('');
		$('#mChanting').val(0);
		$('#stAddress').val('');
		$('#registrationId').val('');
	}
	
}
(function () {
    $('#registrationId').on("change", function() {
       console.log($('#registrationId option:selected').val());
       // your ajax call
       var studentId=$('#registrationId option:selected').val();
       for (let i = 0; i < student_list.length; i++) {
		   if(student_list[i].stStudentId==studentId){
			   var res=student_list[i]
       		console.log(res);
			   $('#stOccupation').val(res.stOccupation);
				$('#userName').val(res.firstName);
				$('#lastName').val(res.lastName);
				$('#email').val(res.email);
				$('#dtBirth').val(milisecondToDateFormat(res.dtBirth));
				$('#mChanting').val(res.mChanting);
				$('#stAddress').val(res.stAddress);
				$('#registrationId').val(res.stStudentId);
				break;
		   }else{
			   $('#stOccupation').val('');
				$('#userName').val('');
				$('#lastName').val('');
				$('#email').val('');
				$('#mChanting').val(0);
				$('#stAddress').val('');
				$('#registrationId').val('');
		   }
		}
    });
});