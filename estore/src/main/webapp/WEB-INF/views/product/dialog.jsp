<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- Trigger the modal with a button -->
<!--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button> -->

<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Send to friend</h4>
      </div>
      <div class="modal-body">
      <div class="form-group">
       		<label>Sender Email</label> 
       		<input id="sender" name="email" class="form-control"/>
       </div> 
      
       <div class="form-group">
       		<label>Recipient Email</label> 
       		<input id="email" name="email" class="form-control"/>
       </div> 
       
        <div class="form-group">
       		<label>Comment</label> 
       		<textarea id="comments" name="comments" class="form-control" row="3"></textarea>
       </div>
       <input type="hidden" id="id"/>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default btn-send">Send</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>