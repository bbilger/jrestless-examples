# JRestless Examples

[![Build Status](https://travis-ci.org/bbilger/jrestless-examples.svg?branch=master)](https://travis-ci.org/bbilger/jrestless-examples)
[![GitHub issues](https://img.shields.io/github/issues/bbilger/jrestless-examples.svg)](https://github.com/bbilger/jrestless-examples/issues)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/bbilger/jrestless-examples/master/LICENSE)

This repository contains examples for [JRestlesss](https://github.com/bbilger/jrestless).

## Preliminary Note

JRestless does not depend on the [serverless framework](https://github.com/serverless/serverless) but it simplifies the necessary AWS configuration tremendously. So all examples contain a `serverless` configuration and the installation descriptions assume you have `serverless` installed and configured.

You can install `serverless` as described in the docs https://serverless.com/framework/docs/guide/installing-serverless/

To run the AWS examples setup your AWS account as described in the docs https://serverless.com/framework/docs/providers/aws/guide/credentials/

## Examples

* [AWS](aws)
  * [API Gateway](aws/gateway)
    * [aws-gateway-showcase](aws/gateway/aws-gateway-showcase)
      * Example showing JRestless' features.
    * [aws-gateway-spring](aws/gateway/aws-gateway-spring)
      * Example showing how to use Spring in JRestless.
    * [aws-gateway-usage-example](aws/gateway/aws-gateway-usage-example)
      * Simple JRestless usage example.
    * [aws-gateway-binary](aws/gateway/aws-gateway-binary)
      * Example how to return and receive binary data.
    * [aws-gateway-security-cognito-authorizer](aws/gateway/aws-gateway-security-cognito-authorizer)
      * Example how to use a cognito user pool authorizer.
    * [aws-gateway-security-custom-authorizer](aws/gateway/aws-gateway-security-custom-authorizer)
      * Example showing how to use a custom authorizer.
  * [Lambda Service Function](aws/service)
    * [aws-service-usage-example](aws/service/aws-service-usage-example)
      * Example showing how to invoke one Lambda (service) function from another (API Gateway).
  * [SNS Function](aws/sns)
    * [aws-sns-usage-example](aws/sns/aws-sns-usage-example)
      * Example showing how to use JRestless to handle SNS notifications.
