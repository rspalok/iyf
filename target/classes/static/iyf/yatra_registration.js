$(document).ready(function() {
					 
					 
}); 

var student_list;
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
    td = tr[i].getElementsByTagName("td")[2];
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
function myFunction1() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput1");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable1");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];
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

function getYatraRagistrationDetails(yatraId,studentId){
	
	console.log("getYatraRagistrationDetails");
	var action = "../studentfromYatra/?studentId=" + studentId+"&yatraCruiseId="+yatraId;
	 
	console.log("alert is working  "+action);
	$.ajax({url: action,type:"GET",async:false,dataType:"json" ,success:function(data) {
			result = viewSingleYatraDtls(data,1);
		},error: function(errorMsg,textstatus,errorthrown) {
			alert('getStudenInfo '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		}
	});
}
function viewSingleYatraDtls(res, mode){
	console.log(res);
	if(res.length >0){
		
		$('#mContributed').val(res[0].contributed);
		$('#contributionMode').val(res[0].contributionMode);
		$('#facilitator').val(res[0].IFacilitator);
		
		
	}else{
		
		$('#mContributed').val(0);
		$('#contributionMode').val('');
		$('#facilitator').val('');
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
	
	//var parsedData = JSON.parse(res);
	console.log("========"+parsedData);
	console.log(res);
	if(res.length >0){
		$('#stOccupation').val(res[0].stOccupation);
		$('#firstName').val(res[0].firstName);
		$('#lastName').val(res[0].lastName);
		$('#email').val(res[0].email);
		$('#mChanting').val(res[0].mChanting);
		$('#stAddress').val(res[0].stAddress);
		$('#stStudentId').val(res[0].stStudentId);
		$('#mOrgUnit').val(res.mOrgUnit);
		
        var yatraId=$('#yatraCruiseId option:selected').val();
		getYatraRagistrationDetails(yatraId,res[0].stStudentId);
	}else{
		$('#stOccupation').val('');
		$('#firstName').val('');
		$('#lastName').val('');
		$('#email').val('');
		$('#mChanting').val(0);
		$('#stAddress').val('');
		$('#stStudentId').val('');
		$('#mOrgUnit').val(0);
	}
	
}
//yatraId,studentId
getRegiteredStudentList = function() {
		var mICourseConfig=$('#mICourseConfig').val();
		//var action = "../follow_up/getStudentForFolloup/?stStudentId="+stStudentId +"&followUpId="+ followUpId;
		var action = "../getRegiteredStudentList/?mICourseConfig="+mICourseConfig;// +"&followUpId="+ followUpId;
		 
		console.log("alert is working  "+action);
		$.ajax({url: action,type:"GET",async:false,dataType:"json" ,success:function(data) {
				result = viewRegDtls(data,1);
			},error: function(errorMsg,textstatus,errorthrown) {
				alert('getStudenInfo '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
			}
		});
	}
	viewRegDtls = function (res, mode) {
		
		var parsedData = res;
		console.log(parsedData);
		$('#reportMainBodyDivId').html("");
		var headerRow="", dtlRow="", footerRow="";
		rptHeadingOrTitle="Notice List Report - Active";
		//var globalRptPrintRow=getRptPrintOption("1");
		//var globalRptHeadingOrTitle=rptHeadingOrTitle;
		//var globalRptHeaderRow=getRptHeader("1");
		//var globalRptDateAndTimeRow=getRptDateAndTime("1",resJsonObj['Date'],resJsonObj['Time'] );
		//var globalRptHeadingRow=getRptHeading("2",globalRptHeadingOrTitle);
		//var globalRptFooterRow=getRptFooter("1");
			
		headerRow="";//globalRptPrintRow+globalRptHeaderRow+globalRptDateAndTimeRow+globalRptHeadingRow;
		$("#reportMainHeaderDivId").html(headerRow);
		footerRow="";//globalRptFooterRow;
		$("#reportMainFooterDivId").html(footerRow);
		$("#reportModalHeadingId").html(rptHeadingOrTitle);	
		
		var parsedData=res;//resJsonObj['Data'];
		var rowCount = parsedData.length;
		dtlRow +=" <h6 class='pull-right green'><strong><span id='countRowsId'>Total Rows : "+ rowCount +"</span></strong></h6> "+
			" <table id='reportTableID' class='table table-bordered'> "+
			" <thead  id='reportTHeadId'> <tr> "+
			" <th>Student Id</th> "+
			" <th>Name</th> "+
			" <th>Mobile</th> "+
			" <th>Address</th> "+
			" <th class='hidden-480'>Profetion</th> "+
			" </tr> </thead> "+
			" <tbody id='reportTBodyId'> ";
			
		for(var i=0;i<parsedData.length;i++)
		{	
			dtlRow +="<tr><td>"+parsedData[i].stStudentId+"</td> " +
			"<td>"+parsedData[i].objGbltOtpStudentRegTrns.firstName+" "+parsedData[i].objGbltOtpStudentRegTrns.lastName+"</td> " +
			"<td>"+parsedData[i].objGbltOtpStudentRegTrns.imobile+"</td> " +
			"<td>"+parsedData[i].objGbltOtpStudentRegTrns.stAddress+"</td> " +
			"<td>"+parsedData[i].objGbltOtpStudentRegTrns.stOccupation+"</td> " +
			//"<td class='hidden-480'>"+returnParsedDate(parsedData[i].m_dtEntry)+"</td> " +
			"</tr>";//<span class='"+parsedData[i].cssClass+"'>"+parsedData[i].m_stStatus+"</span>
		}
		dtlRow +=" </tbody> </table> ";
		
		$('#reportMainBodyDivId').html(dtlRow);
		$('#myModal').modal('show'); 
		//setTimeout(function() {
		//	$("#wait").hide();
		//}, 500);
		
	}
$(function () {
     $('#stStudentId').on("change", function() {
        console.log($('#stStudentId option:selected').val());
        // your ajax call
        var studentId=$('#stStudentId option:selected').val();
        var yatraId=$('#yatraCruiseId option:selected').val();
        for (let i = 0; i < student_list.length; i++) {
		   if(student_list[i].stStudentId==studentId){
			   var res=student_list[i]
        		console.log(res);
			   $('#stOccupation').val(res.stOccupation);
				$('#firstName').val(res.firstName);
				$('#lastName').val(res.lastName);
				$('#email').val(res.email);
				$('#dtBirth').val(res.dtBirth);
				$('#mChanting').val(res.mChanting);
				$('#stAddress').val(res.stAddress);
				$('#stStudentId').val(res.stStudentId);
				$('#mOrgUnit').val(res.mOrgUnit);
				getYatraRagistrationDetails(yatraId,studentId);
				break;
		   }else{
			   $('#stOccupation').val('');
				$('#firstName').val('');
				$('#lastName').val('');
				$('#email').val('');
				$('#mChanting').val(0);
				$('#stAddress').val('');
				$('#stStudentId').val('');
				$('#mOrgUnit').val(0);
		   }
		}
     });
});
	 