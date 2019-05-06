# Generated by Django 2.2 on 2019-05-06 15:00

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('polls', '0003_auto_20190331_1818'),
    ]

    operations = [
        migrations.CreateModel(
            name='Review',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('score', models.FloatField()),
                ('comments', models.CharField(max_length=200)),
                ('restaurant', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='polls.Restaurant')),
            ],
        ),
    ]
