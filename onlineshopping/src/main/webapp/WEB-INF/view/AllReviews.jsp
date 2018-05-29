<div class="container">
	<c:forEach items="${reviews}" var="review">
		<div class="media">
			<div class="media-left">

				<img src="" class="media-object" style="width: 60px color:blue" />

			</div>
			<div class="media-body">

				<h4 class="media-heading">${review.userName}:</h4><p>${review.productReview}</p>
				

			</div>
		</div>
	</c:forEach>
	
	<!--<div  class="row">
	
	<button class="btn btn-primary col-md-offset-4 col-md-2">View Result</button>
	
	</div>  -->
</div>