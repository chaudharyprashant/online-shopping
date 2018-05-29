	<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">

	<!-- breadcrumb -->

	<div class="row">
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li><a href="${contextRoot}/home">Home</a>
				<li><a href="${contextRoot}/show/all/product">Products</a></li>
				<li class="active">${product.name}</li>
			</ol>
		</div>
	</div>
   <script>



// Define a couple of global variables so we can easily inspect data points we guessed incorrectly on.
var incorrectNegs = [];
var incorrectPos = [];

// A list of negation terms that we'll use to flag nearby tokens
var negations = new RegExp("^(never|no|nothing|nowhere|noone|none|not|havent|hasnt|hadnt|cant|couldnt|shouldnt|wont|wouldnt|dont|doesnt|didnt|isnt|arent|aint)$");

// Use 85% of our data set for training, the remaining 15% will be used for testing.
var length = negatives.length;
var split = Math.floor(0.85 * length);

// Don't spit out console.log stuff during training and guessing. 
Bayes.debug = false;

// Close-proximity negation-marked unigram ("eMSU")
Bayes.tokenizer = function (text) {
    // Standard unigram tokenizer; lowercase, strip special characters, split by whitespace
    text = Bayes.unigramTokenizer(text);
    // Step through our array of tokens
    for (var i = 0, len = text.length; i < len; i++) {
        // If we find a negation word, add an exclamation point to the word preceding and following it.
        if (text[i].match(negations)) {
            if (typeof text[i + 1] !== 'undefined') text[i + 1] = "!" + text[i + 1];
            if (typeof text[i - 1] !== 'undefined') text[i - 1] = "!" + text[i - 1];
        }
    }
    // Porter Stemmer; this reduces entropy a bit
    text = text.map(function (t) { if(wordbag[t] != undefined) 
    	{return t ;} 
    return t;
    	//return stemmer(t) 
    	});
    return text;
};

// Set the storage engine to in-memory; this example has too much data for localStorage.
Bayes.storage = Storage;

// Runs a single training and testing experiment.
function go() {

    // Start from scratch.
    var correct = 0;
    var incorrect = 0;
    var skipped = 0;
    
    var resultsPct = 0.0;
    
    Bayes.storage._data = {};

    // Shuffle our arrays. I'm sure some really astute CS genius will find a flaw with this ;)
    negatives.sort(function () { return Math.random() - 0.5; });
    positives.sort(function () { return Math.random() - 0.5; });

    // First we train. Walk through the data until we hit our split/pivot point.
    // Unfortunately our progress bar doesn't work because of the browser's JS event loop,
    // And retooling to use animation frames is more annoying than it's worth.
    
    for (var i = 0; i < split; i++) {
        Bayes.train(negatives[i], 'negative');
        Bayes.train(positives[i], 'positive');
        if (i % 500 === 0) {       
            // Next three lines are largely useless.
            trainingPct = Math.round(i*100 / split);
            
            // If you want live updates, look at the console.
            console.log("Training progress: " + trainingPct + "%");
        }
    }
    
    
    
   
}

window.onload = function() {
	

	go();
	console.log("gocall");
   
};


		</script>
  <script>
 var analyze = function(){ 
	 
			var text = document.getElementById("product_Review").value;
			var result = Bayes.extractWinner(Bayes.guess(text));
			var awords = Bayes.tokenizer(text);
			var sum = 0 ;
			awords.forEach(function(word){
				console.log(word);
				console.log(wordbag[word]);
	       if(wordbag[word] != undefined)
				 { console.log(word);
	    	      sum +=wordbag[word];
				 }
			});
			if(Math.round(100*result.score)<=70)
			{
				if(sum>0)
				{    console.log(sum);
					result.label = "positive"
				}
				else {
					console.log(sum);
					result.label = "negative";
				}
			}
			document.getElementById("checkreview").value = result.label;
			console.log(Math.round(100*result.score));
            alert(result.label);
 }
  </script> 
 
	<div class="row">
		<!-- display the product image -->
		<div class="col-xs-12 col-sm-4">
	    
			<div class="thumbnail">
				<img src="${images}/${product.code}.jpg" class="img img-responsive" />
			</div>
		</div>
		<!-- product description -->
		<div class="col-xs-12 col-sm-8">
             <p id="pc" style = "display:none">${product.positiveCount}</p>
              <p id="nc" style = "display:none">${product.negativeCount}</p>
			<h3>${product.name}</h3>
			<br/>
			
			<i id ="1" class="fa fa-star" style="font-size:40px;color:#ff9900"></i>
			<i id = "2" class="fa fa-star" style="font-size:40px;color:#ff9900"></i>
			<i id = "3" class="fa fa-star" style="font-size:40px;color:#ff9900"></i>
			<i id = "4" class="fa fa-star" style="font-size:40px;color:#ff9900"></i>
			<i id = "5" class="fa fa-star" style="font-size:40px;color:#ff9900"></i>
			<hr />
			<p>${product.description}</p>
			<hr />
			<h4>
				Price :<strong> &#8377; ${product.unitPrice} /-</strong>
			</h4>
		
			<hr />

			<c:choose>

				<c:when test="${product.quantity<1}">
					<h6>
						Qty.Available:<span style="color: red">Out of Stock</span>
					</h6>
				</c:when>
				<c:otherwise>
					<h6>Qty.Available : ${product.quantity}</h6>
				</c:otherwise>
			</c:choose>
          <security:authorize access="hasAuthority('USER')">
			<c:choose>

				<c:when test="${product.quantity<1}">
					<a href="javascript:void(0)" class="btn btn-success disabled"><strike><span
							class="glyphicon glyphicon-shopping-cart"></span>Add to Cart</strike></a>
				</c:when>
				<c:otherwise>
					<a href="${contextRoot}/cart/add/${product.id}/product"
						class="btn btn-success"><span
						class="glyphicon glyphicon-shopping-cart"></span>Add to Cart</a>
				</c:otherwise>
			</c:choose>
			</security:authorize>
			
			<security:authorize access="hasAuthority('ADMIN')">
			
			<a href="${contextRoot}/manage/${product.id}/product"
						class="btn btn-warning"><span
						class="glyphicon glyphicon-pencil"></span>Edit</a>
			
			</security:authorize>
			
			
			<a href="${contextRoot}/show/all/products" class="btn btn-primary">Back</a>
			<a href="${contextRoot}/view/product/${product.id}/reviews" class="btn btn-info " role="button">View Review</a>

		</div>
	</div>
	<h4 style="color: blue">Write Review About the Product</h4>
    <h4 style ="color:green">${message}</h4>

	<sf:form class="form-horizontal" modelAttribute="review"
		action="${contextRoot}/submit/${product.id}/review" onsubmit = "return analyze()" method="POST">
		<div class="form-group">
			
			<div class="col-md-10">
				<sf:input type="hidden" value="${userModel.fullName}"  path="userName" placeholder="user name"
					id="user_name" class="form-control"></sf:input>
			</div>
		</div>
		
		<div class="form-group">
			
			<div class="col-md-10">
				<sf:input type="hidden" value="${product.id}" path="productId"
					id="product_id" class="form-control"></sf:input>
			</div>
		</div>
		<div class="form-group">
			
			<div class="col-md-10">
				<sf:input type="hidden" value="${product.categoryId}" path="categoryId"
					id="product_id" class="form-control"></sf:input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" id="productReview">Enter
				Review:</label>
			<div class="col-sm-10">
               
				<sf:textarea path="productReview"
					placeholder="Enter review of the product (limit 255 characters)" class="form-control"
					rows="6" maxlength = "255" id="product_Review"></sf:textarea>
			</div>
		</div> 
		  <sf:input path = "reviewClass"  type="hidden"  id="checkreview" name="reviewClass"></sf:input>
		<div class="form-group">
			<input type="submit" name="submit" id="submit" value="submit"
				class="btn btn-primary col-md-offset-4" />
			<sf:hidden path="reviewId" />
		</div>
	</sf:form>
	<script> 
	 var pc =  document.getElementById("pc").innerHTML; 
	   pc  = parseInt(pc);
	 var nc =  document.getElementById("nc").innerHTML; 
	      nc =  parseInt(nc);
	 var tc  = pc + nc; 
	 var rt = pc/tc; 
	 var rt  = rt*5; 
	 var irt =  parseInt(rt);
     if(rt>irt) 
    {    irt= irt +1; 
    	 document.getElementById(irt).setAttribute("class", "fa fa-star-half-full");
    	 for(var i = irt+1 ;i<=5;i++)
    		 { 
    		 document.getElementById(i).setAttribute("class", "fa fa-star-o");
    		 }
     }
     else 
    	 { 
    	 for(var i = irt+1 ;i<=5;i++)
		 { 
		 document.getElementById(i).setAttribute("class", "fa fa-star-o");
		 }
    	 }
	</script>
</div>
