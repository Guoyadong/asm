var Helper = 
{
	validateVersion:function(version){
        var pattern1 = /^[0-9]{1,3}(\.[0-9]{1,3})?(\.[0-9]{1,3})?$/;
        var pattern2 = /^[0-9]{1,3}(\.[0-9]{1,3})(\.[0-9]{1,3})(\.[0-9]{1,8})?$/;
        return pattern1.test(version) || pattern2.test(version);
	    
	},
	validateUrl:function(url){
        var pattern = /^(http|ftp|https):\/\/[\w\-_]+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;
                return pattern.test(url);		
	}
	
}