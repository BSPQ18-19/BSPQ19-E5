# -*- coding: utf-8 -*-
# Generated by Django 1.10.5 on 2019-03-31 16:18
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('polls', '0002_auto_20190331_1811'),
    ]

    operations = [
        migrations.RenameField(
            model_name='reservation',
            old_name='number_p',
            new_name='number_clients',
        ),
    ]