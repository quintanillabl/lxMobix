<li class="dropdown">
	<a class="dropdown-toggle" data-toggle="dropdown" href="#">
		<i class="fa fa-bars"></i>
		Catalogos <b class="caret"></b>
	</a>
	<ul class="dropdown-menu" role="menu">
		%{-- <nav:menu id="nav"  scope="user/catalogos"/> --}%
		<nav:menu id="nav"  scope="user/catalogos" custom="true">
			<li>
				<p:callTag tag="g:link"
				           attrs="${linkArgs + [class:active ? 'active' : '']}">
				  
				   <i class="${item.data.icon ?:''}"></i>&nbsp;<nav:title item="${item}"/>
				</p:callTag>
			</li>
		    
		</nav:menu> 
	</ul>
</li>
