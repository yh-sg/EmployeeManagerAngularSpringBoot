import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { EmployeeService } from '../employee.service';
import { Employee } from '../model';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  public employees: Employee[];
  public editEmployee: Employee;
  public deleteEmployee: Employee;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
      (response: Employee[])=>{
        this.employees = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddEmployee(addForm: NgForm):void{
    document.getElementById('add-employee-form').click()
    this.employeeService.addEmployees(addForm.value).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
        addForm.reset()
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
        addForm.reset()
      }
    )
  }

  public updateEmployee(employee: Employee):void{
    this.employeeService.updateEmployees(employee).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    )
  }

  public onDeleteEmloyee(id: number):void{
    this.employeeService.deleteEmployees(id).subscribe(
      (response: void) => {
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    )
  }

  public searchEmployees(key: string): void{
    console.log(key);
    const results: Employee[] = [];
    this.employees.forEach(employee => {
      if(employee.name.toLowerCase().indexOf(key.toLowerCase())!==-1
      ||employee.email.toLowerCase().indexOf(key.toLowerCase())!==-1
      ||employee.phone.toLowerCase().indexOf(key.toLowerCase())!==-1
      ||employee.jobTitle.toLowerCase().indexOf(key.toLowerCase())!==-1){
        results.push(employee);
      }
    });
    this.employees = results;
    if(results.length === 0 || !key){
      this.getEmployees();
    } 
  }

  public openModal(employee: Employee, mode: string):void {
    const container = document.getElementById("main-container");
    //From bootstrap modal live demo button!
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal')

    if(mode==='add')
      button.setAttribute('data-target', '#addEmployeeModal')

    if(mode==='update'){
      this.editEmployee = employee
      button.setAttribute('data-target', '#updateEmployeeModal')
    }
      
    if(mode==='delete'){
      this.deleteEmployee = employee
      button.setAttribute('data-target', '#deleteEmployeeModal')

    }
      container.appendChild(button);
      button.click();
  }

}
