package com.codeaffine.workflow;

import com.codeaffine.workflow.definition.VariableDeclaration;
import com.codeaffine.workflow.event.WorkflowContextListener;


public interface WorkflowContext {

  public static final VariableDeclaration<WorkflowContext> VARIABLE_CONTEXT
    = new VariableDeclaration<WorkflowContext>( "workflowContext", WorkflowContext.class );
  public static final VariableDeclaration<WorkflowService> VARIABLE_SERVICE
    = new VariableDeclaration<WorkflowService>( "workflowService", WorkflowService.class );
  public static final VariableDeclaration<TaskList> VARIABLE_TASK_LIST
    = new VariableDeclaration<TaskList>( "taskList", TaskList.class );

  <T> void defineVariable( VariableDeclaration<T> declaration, T value );
  boolean hasVariableDefinition( VariableDeclaration<?> declaration );
  <T> T getVariableValue( VariableDeclaration<T> declaration );
  VariableDeclaration<?>[] getVariableDeclarations();

  void addWorkflowContextListener( WorkflowContextListener workflowContextListener );
  void removeWorkflowContextListener( WorkflowContextListener workflowContextListener );
}