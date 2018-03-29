<div class="container">
	<!-- would be to display side bar -->
	<div class="row">

		<div class="col-md-3">
			<%@ include file="./shared/sidebar.jsp"%>
		</div>
		<!-- to display the actual products -->

		<div class="col-md-9">
			<!-- added breadcrumb component -->
			<div class="row">

				<div class="col-lg-12">

					<c:if test="${userClickAllProducts==true }">

						<script>
							window.categoryId = '';
							console.log('in userclick all product');
						</script>
						<ol class="breadcrumb">

							<li><a href="${contextRoot}/home">HOME</a>
							<li class="active">All Products</li>
						</ol>

					</c:if>


					<c:if test="${userClickCategoryProducts==true }">

						<script>
							window.categoryId = '${category.id}';
							console.log(window.categoryId);
						</script>

						<ol class="breadcrumb">

							<li><a href="${contextRoot}/home">HOME</a>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>
						</ol>

					</c:if>
				</div>


			</div>

			<div class="row">
				<div class="col-xs-12">

					<table id="productListTable"
						class="table table-striped table-borderd">

						<thead>
							<tr>

                                <th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Qty.Available</th>
								<th></th>
							</tr>
						</thead>
						<tfoot>
							<tr>

                                <th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Qty.Available</th>
								<th></th>
							</tr>
						</tfoot>

					</table>

				</div>

			</div>

		</div>
	</div>
</div>