import { Component, OnInit, Inject } from '@angular/core';
import { ResultService } from '../service/result/result.service';
import { Result } from '../models/agile/Result';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import {
  FormControl,
  FormGroupDirective,
  FormBuilder,
  FormGroup,
  NgForm,
  Validators
} from '@angular/forms';
import { AlertService } from '../service';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogConfig } from '@angular/material';
import { DialogData } from '../models/DialogData';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';
import { User } from '../models/user/User';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.scss']
})
export class ResultComponent implements OnInit {

  resultForm: FormGroup;
  results: Array<Result> = [];
  description: string;
  flag: boolean = false;

  constructor(private formBuilder: FormBuilder, private http: HttpClient
    , private route: Router, private resultService: ResultService, private alertService: AlertService) { }

  ngOnInit() {
    this.resultForm = this.formBuilder.group({
      'description': [null, [Validators.required , Validators.min(3)]]
    });
    this.resultService.getAllResults().subscribe(data => { this.results = data; })
  }

  onFormSubmit(form: NgForm): void {
    if (this.resultForm.invalid) return;
    this.resultService.createResult(form).subscribe(data => console.log(data));
  }
}
