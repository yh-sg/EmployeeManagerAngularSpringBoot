import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../model';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  public employees: Employee[];

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

  public openModal(employee: Employee, mode: string):void {
    const container = document.getElementById("main-container");
    //From bootstrap modal live demo button!
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal')

    if(mode==='add')
      button.setAttribute('data-target', '#addEmployeeModal')

    if(mode==='update')
      button.setAttribute('data-target', '#updateEmployeeModal')
      
    if(mode==='delete')
      button.setAttribute('data-target', '#deleteEmployeeModal')

      container.appendChild(button);
      button.click();
  }

}
