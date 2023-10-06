function checkFormDatabase(mobileNo){
	if(mobileNo.length <10){
		return;
	}
	var action = "../report/studentByMobileNo/?mobileNumber=" + mobileNo;
	 alert(action);
	console.log("alert is working  "+action);
	$.ajax({url: action,type:"GET",async:false,dataType:"json" ,success:function(data) {
			result = viewSingleDtls(data,1);
		},error: function(errorMsg,textstatus,errorthrown) {
			alert('getStudenInfo '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		}
	});
}

function viewSingleDtls(res, mode) {
	//var parsedData = JSON.parse(res);
	//console.log("========"+parsedData);
	student_list=res;
	var parsedData = res;
	console.log(parsedData);
	var temp = "<option value=''>New </option>";
	for (var i = 0; i < parsedData.length; i++) {
		temp += "<option value='" + parsedData[i].stStudentId + "'>"
			+ parsedData[i].firstName+" "+ parsedData[i].lastName+ " ( "+parsedData[i].stStudentId +" )"+" </option>";
	}
	$('#stStudentId').html(temp);
	setTimeout(function() {
		$("#wait").hide();
	}, 500);
	
	
	console.log(res);
	if(res.length >0){
		$('#stOccupation').val(res[0].stOccupation);
		$('#firstName').val(res[0].firstName);
		$('#lastName').val(res[0].lastName);
		$('#email').val(res[0].email);
		$('#mChanting').val(res[0].mChanting);
		$('#stAddress').val(res[0].stAddress);
		$('#stStudentId').val(res[0].stStudentId);
	}else{
		$('#stOccupation').val('');
		$('#firstName').val('');
		$('#lastName').val('');
		$('#email').val('');
		$('#mChanting').val(0);
		$('#stAddress').val('');
		$('#stStudentId').val('');
	}
	function getDetail(){
		
	}
}