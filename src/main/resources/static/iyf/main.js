//alert("alert is working");
var student_list;
function checkFormDatabase(id){
	if(id.length <10){
		return;
	}
	var action = "../studentByMobileNo/?mobileNumber=" + id;
	 
	//alert("alert is working  "+action);
	$.ajax({url: action,type:"GET",async:false,dataType:"json" ,success:function(data) {
			result = viewSingleDtls(data,1);
		},error: function(errorMsg,textstatus,errorthrown) {
			alert('getStudenInfo '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		}
	});
}

function viewSingleDtls(res, mode) {
	//var parsedData = JSON.parse(res); 
	student_list=res;
	var parsedData = res;
	console.log(parsedData);
	var temp = "<option value=''>New</option>";
	for (var i = 0; i < parsedData.length; i++) {
		
		temp += "<option value='" + parsedData[i].stStudentId + "'>"
			+ parsedData[i].firstName+" "+ parsedData[i].lastName+ " ( "+parsedData[i].stStudentId +" )"+" </option>";
	}
	$('#stStudentId').html(temp);
	setTimeout(function() {
		$("#wait").hide();
	}, 500);
	

	if(res.length >0){
		$('#stOccupation').val(res[0].stOccupation);
		$('#firstName').val(res[0].firstName);
		$('#lastName').val(res[0].lastName);
		$('#email').val(res[0].email);//$("#date").val( moment().format('MMM D, YYYY') );
		$('#dtBirth').val(milisecondToDateFormat(res[0].dtBirth));
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
	
}

$(function () {
     $('#stStudentId').on("change", function() {
        console.log($('#stStudentId option:selected').val());
        // your ajax call
        var studentId=$('#stStudentId option:selected').val();
        for (let i = 0; i < student_list.length; i++) {
		   if(student_list[i].stStudentId==studentId){
			   var res=student_list[i]
        		console.log(res);
			   $('#stOccupation').val(res.stOccupation);
				$('#firstName').val(res.firstName);
				$('#lastName').val(res.lastName);
				$('#email').val(res.email);
				$('#dtBirth').val(milisecondToDateFormat(res.dtBirth));
				$('#mChanting').val(res.mChanting);
				$('#stAddress').val(res.stAddress);
				$('#stStudentId').val(res.stStudentId);
				break;
		   }else{
			   $('#stOccupation').val('');
				$('#firstName').val('');
				$('#lastName').val('');
				$('#email').val('');
				$('#mChanting').val(0);
				$('#stAddress').val('');
				$('#stStudentId').val('');
		   }
		}
     });
});
function milisecondToDateFormat(dateDetails){
	var date = new Date(dateDetails); // Date 2011-05-09T06:08:45.178Z
	var year = date.getFullYear();
	var month = ("0" + (date.getMonth() + 1)).slice(-2);
	var day = ("0" + date.getDate()).slice(-2);
	    
	console.log(`${year}-${month}-${day}`); // 2011-05-09
	if(dateDetails==null){
		return null;
	}
	return day+"/"+month+"/"+year;
}
