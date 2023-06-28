$(document).ready(function() {
					 
					 
}); 
function updateAyyendance(id){
				
	
	$("#stStudentId").val(id); 
	$( "#target" ).submit();

}
function getDetail(){
	var mClassId=$('#mClassId').val();
	var mICourseConfig=$('#mICourseConfig').val(); 
	if(mClassId=="" && mICourseConfig==""){
		//$( "#target" ).attr();
		$( "#target" ).submit();
	}
}
				
function myFunction() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}	
function checkFormDatabase(id){
	if(id.length <10){
		return;
	}
	var action = "../studentByMobileNo/?mobileNumber=" + id;
	 
	console.log("alert is working  "+action);
	$.ajax({url: action,type:"GET",async:false,dataType:"json" ,success:function(data) {
			result = viewSingleDtls(data,1);
		},error: function(errorMsg,textstatus,errorthrown) {
			alert('getStudenInfo '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		}
	});
}

function viewSingleDtls(res, mode) {
	
	var parsedData = res;
	console.log(parsedData);
	var temp = "<option value=''>Select</option>";
	for (var i = 0; i < parsedData.length; i++) {
		temp += "<option value='" + parsedData[i].stStudentId + "'>"
			+ parsedData[i].firstName+" "+ parsedData[i].lastName+ " ( "+parsedData[i].stStudentId +" )"+" </option>";
	}
	$('#stStudentId').html(temp);
	setTimeout(function() {
		$("#wait").hide();
	}, 500);
	
	//var parsedData = JSON.parse(res);
	//console.log("========"+parsedData);
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
	
}
	 