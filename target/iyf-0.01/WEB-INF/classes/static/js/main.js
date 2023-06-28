//alert("alert is working");
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
	
	
	 /*
	[{"stStudentId":"IYF20230204421","stOrgId":"PNB108","stOwnerId":"ALOK108","firstName":"","lastName":"","email":"","stAddress":"",
	"mChanting":null,"stOccupation":"","stStage":null,"dtRegistration":1677349800000,"dtEntry":1677349800000,"icounselor":null,
	"ifacilitator":null,"iisValid":1,"imobile":8669011448},
	{"stStudentId":"IYF20230204422","stOrgId":"PNB108","stOwnerId":"ALOK108","firstName":"","lastName":"","email":"","stAddress":"","mChanting":null,"stOccupation":"","stStage":null,"dtRegistration":1677349800000,"dtEntry":1677349800000,"icounselor":null,"ifacilitator":null,"iisValid":1,"imobile":8669011448},
	{"stStudentId":"2023100003","stOrgId":"PNB108","stOwnerId":"ALOK108","firstName":"ALOK","lastName":"SHARMA","email":"rspalok@gmail.com","stAddress":"Anisandhan bhawn","mChanting":null,"stOccupation":null,"stStage":null,"dtRegistration":1673980200000,"dtEntry":1673980200000,"icounselor":null,"ifacilitator":null,"iisValid":1,"imobile":8669011448}]
 */
}